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
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;

public class ClientSearchServletTest extends Mockito {

    private final ClientCache clientCache = ClientCache.getInstance();

    private static ClientSearchServlet servlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private RequestDispatcher dispatcher;

    private Client client1;
    private Client client2;
    private Client client3;

    public ClientSearchServletTest() {
        // Создаём несколько клиентов
        client1 = new Client(1, "clientName", new Pet(1, "petType", "petName", "petSex", "5"));
        client2 = new Client(2, "clientName", new Pet(2, "petType", "petName", "petSex", ""));
        client3 = new Client(3, "clientName", new Pet(3, "petType", "petName", "petSex", "5"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        servlet = new ClientSearchServlet();
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

        // Добавляем клиентов в базу
        clientCache.add(client1);
        clientCache.add(client2);
        clientCache.add(client3);
    }

    /**
     * Проверяем, что всё работает по нужному сценарию
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("1");

        servlet.doGet(request, response);

        verify(request).setCharacterEncoding("UTF-8");
        verify(request).setAttribute(anyString(), anyObject());
        verify(request).getRequestDispatcher(anyString());
        verify(dispatcher).forward(request, response);
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyNoMoreInteractions(dispatcher);
    }

    /**
     * Проверяем, что если пользователь заполнит только поле id клиента,
     * то искомый клиент найдется и добавится в список
     */
    @Test
    public void testDoPostFindById() throws ServletException, IOException {
        when(request.getParameter("idClient")).thenReturn(String.valueOf(client2.getId()));
        when(request.getParameter("clientName")).thenReturn(null);
        when(request.getParameter("petName")).thenReturn(null);
        when(request.getParameter("petAge")).thenReturn(null);

        servlet.doPost(request, response);

        assertThat(clientCache.valuesFound(), hasItem(client2));
        assertThat(clientCache.valuesFound(), not(hasItems(client1, client3)));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(4)).getParameter(anyString());
        verify(request, atMost(4)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }

    /**
     * Проверяем, что если пользователь заполнит все поля кроме id клиента,
     * то искомые клиенты найдутся и добавятся в список
     */
    @Test
    public void testDoPostFindByThreeFields() throws ServletException, IOException {
        when(request.getParameter("idClient")).thenReturn("");
        when(request.getParameter("clientName")).thenReturn(client1.getName());
        when(request.getParameter("petName")).thenReturn(client1.getPet().getName());
        when(request.getParameter("petAge")).thenReturn(client1.getPet().getAge());

        servlet.doPost(request, response);

        assertThat(clientCache.valuesFound(), not(hasItem(client2)));
        assertThat(clientCache.valuesFound(), hasItems(client1, client3));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(4)).getParameter(anyString());
        verify(request, atMost(4)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }
}