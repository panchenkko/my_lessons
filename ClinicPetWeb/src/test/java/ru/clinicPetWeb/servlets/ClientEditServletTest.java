package ru.clinicPetWeb.servlets;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ru.clinicPetWeb.models.Client;
import ru.clinicPetWeb.models.Pet;
import ru.clinicPetWeb.store.ClientCache;
import ru.clinicPetWeb.store.MemoryStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class ClientEditServletTest extends Mockito {

    private final ClientCache clientCache = ClientCache.getInstance();

    private static ClientEditServlet servlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private RequestDispatcher dispatcher;

    private Client clientOld;
    private Client clientNew;

    public ClientEditServletTest() {
        // Создаём нового и старого клиента, замени старого на нового
        clientOld = new Client(1, "clientName1", new Pet(1, "petType1", "petName1", "petSex1", "1"));
        clientNew = new Client(1, "New", new Pet(1, "New", "New", "New", "10"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        servlet = new ClientEditServlet();
    }

    @Before
    public void setUp() throws Exception {
        // Создаём моки
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);

        // Устанавливаем тип хранилища
        clientCache.setStorage(new MemoryStorage());

        // Очищаем бд
        clientCache.foldCounters();

        // Добавляем старого клиента в базу
        clientCache.add(clientOld);
    }

    /**
     * Проверяем, что всё работает по нужному сценарию и получать будем клиента с нужным id
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("1");

        servlet.doGet(request, response);

        verify(request).setCharacterEncoding("UTF-8");
        verify(request).getRequestDispatcher(anyString());
        verify(request).getParameter("id");
        verify(request).setAttribute("client", clientOld);
        verify(dispatcher).forward(request, response);
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyNoMoreInteractions(dispatcher);
    }

    /**
     * Проверяем, что если попытаемся отредактировать данные клиента, какого не существует,
     * то выдаст ошибку
     */
    @Test
    public void testDoGetException() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("2");

        servlet.doGet(request, response);

        verify(request).setCharacterEncoding("UTF-8");
        verify(request).getParameter("id");
        verify(request).setAttribute(anyString(), anyObject());
        verify(request).getRequestDispatcher(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }

    /**
     * Проверяем, что редактирование клиента происходит успешно
     */
    @Test
    public void testDoPost() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(String.valueOf(clientNew.getId()));
        when(request.getParameter("clientName")).thenReturn(clientNew.getName());

        when(request.getParameter("pet_id")).thenReturn(String.valueOf(clientNew.getPet().getId()));
        when(request.getParameter("petType")).thenReturn(clientNew.getPet().getType());
        when(request.getParameter("petName")).thenReturn(clientNew.getPet().getName());
        when(request.getParameter("petSex")).thenReturn(clientNew.getPet().getSex());
        when(request.getParameter("petAge")).thenReturn(clientNew.getPet().getAge());

        servlet.doPost(request, response);

        assertThat(clientCache.values(), not(hasItem(clientOld)));
        assertThat(clientCache.values(), hasItem(clientNew));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(7)).getParameter(anyString());
        verify(request, atMost(7)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }

    /**
     * Проверяем, что если мы вносим только самые необходимые данные клиента,
     * то в остальных полях добавится прочерк и клиент отредактируется
     */
    @Test
    public void testDoPostForOtherBranches() throws Exception {
        // Изменяем поля нового клиента, для проверки, что трансформация текста в методе doPost() произошла
        clientNew.getPet().setType(" - ");
        clientNew.getPet().setSex(" - ");
        clientNew.getPet().setAge(" - ");

        when(request.getParameter("id")).thenReturn(String.valueOf(clientNew.getId()));
        when(request.getParameter("clientName")).thenReturn(clientNew.getName());

        // Передаем не полные данные о клиенте. Поля какие не заполнены должны измениться на " - "
        when(request.getParameter("pet_id")).thenReturn(String.valueOf(clientNew.getPet().getId()));
        when(request.getParameter("petType")).thenReturn("");
        when(request.getParameter("petName")).thenReturn(clientNew.getPet().getName());
        when(request.getParameter("petSex")).thenReturn(null);
        when(request.getParameter("petAge")).thenReturn(null);

        servlet.doPost(request, response);

        // Сначала проверяем, нету ли старого клиента в базе, после проверяем, произошла ли трансформация данных
        assertThat(clientCache.values(), not(hasItem(clientOld)));
        assertThat(clientCache.values(), hasItem(clientNew));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(7)).getParameter(anyString());
        verify(request, atMost(7)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }
}