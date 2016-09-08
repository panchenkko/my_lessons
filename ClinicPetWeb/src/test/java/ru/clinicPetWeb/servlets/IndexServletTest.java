package ru.clinicPetWeb.servlets;

import org.junit.*;
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
import static org.junit.Assert.assertNull;

public class IndexServletTest extends Mockito {

    private final ClientCache clientCache = ClientCache.getInstance();

    private static IndexServlet servlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private RequestDispatcher dispatcher;

    private Client client;

    public IndexServletTest() {
        // Создаём несколько клиентов
        client = new Client(1, "clientName", new Pet(1, "petType", "petName", "petSex", "5"));
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        servlet = new IndexServlet();
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
    }

    @AfterClass
    public static void tearDown() throws Exception {
        servlet.destroy();
    }

    /**
     * Проверяем, что всё работает по нужному сценарию
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

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
     * Проверяем, что добавление клиента происходит успешно
     */
    @Test
    public void testDoPost() throws ServletException, IOException {
        when(request.getParameter("clientName")).thenReturn(client.getName());

        when(request.getParameter("petType")).thenReturn(client.getPet().getType());
        when(request.getParameter("petName")).thenReturn(client.getPet().getName());
        when(request.getParameter("petSex")).thenReturn(client.getPet().getSex());
        when(request.getParameter("petAge")).thenReturn(client.getPet().getAge());

        servlet.doPost(request, response);

        assertThat(clientCache.values(), hasItem(client));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(5)).getParameter(anyString());
        verify(request, atMost(5)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }

    /**
     * Проверяем, что если мы вносим только самые необходимые данные клиента,
     * то в остальных полях добавится прочерк и клиент добавится
     */
    @Test
    public void testDoPostForOtherBranches() throws Exception {
        // Изменяем поля клиента, для проверки, что трансформация текста в методе doPost() произошла
        client.getPet().setType(" - ");
        client.getPet().setSex(" - ");
        client.getPet().setAge(" - ");

        when(request.getParameter("clientName")).thenReturn(client.getName());

        // Передаем не полные данные о клиенте. Поля какие не заполнены должны измениться на " - "
        when(request.getParameter("petType")).thenReturn("");
        when(request.getParameter("petName")).thenReturn(client.getPet().getName());
        when(request.getParameter("petSex")).thenReturn(null);
        when(request.getParameter("petAge")).thenReturn(null);

        servlet.doPost(request, response);

        // Проверяем, произошла ли трансформация данных
        assertThat(clientCache.values(), hasItem(client));

        verify(request).setCharacterEncoding("UTF-8");
        verify(request, atLeast(5)).getParameter(anyString());
        verify(request, atMost(5)).getParameter(anyString());
        verify(request, atLeast(2)).getContextPath();
        verify(response).sendRedirect(anyString());
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyZeroInteractions(dispatcher);
    }
}