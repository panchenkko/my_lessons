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

public class ClientDeleteServletTest extends Mockito {

    private final ClientCache clientCache = ClientCache.getInstance();

    private static ClientDeleteServlet servlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private RequestDispatcher dispatcher;

    private Client client1;
    private Client client2;

    public ClientDeleteServletTest() {
        // Создаём двух клиентов
        client1 = new Client(1, "clientName1", new Pet(1, "petType1", "petName1", "petSex1", "1"));
        client2 = new Client(2, "clientName2", new Pet(2, "petType2", "petName2", "petSex2", "2"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        servlet = new ClientDeleteServlet();
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

        // Вносим всех клиентов в базу
        clientCache.add(client1);
        clientCache.add(client2);
    }

    /**
     * Проверяем, что всё работает по нужному сценарию и удаляться будет клиент с нужным id
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");

        servlet.doGet(request, response);

        assertThat(clientCache.values(), not(hasItem(client1)));
        assertThat(clientCache.values(), hasItem(client2));

        verify(request).getParameter("id");
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }

    /**
     * Проверяем, что если попытаемся удалить два раза одного и того же клиента,
     * то выдаст ошибку
     */
    @Test(expected = NullPointerException.class)
    public void testDoGetException() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1").thenReturn("1");

        servlet.doGet(request, response);
        servlet.doGet(request, response);
    }
}