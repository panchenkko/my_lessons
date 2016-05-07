//package ru.clinicPetWeb.store;
//
//import org.junit.Test;
//import ru.clinicPetWeb.models.Client;
//
//import static org.junit.Assert.assertNull;
//
//public class HibernateStorageTest {
//	@Test
//	public void testCreate() throws Exception {
//		final HibernateStorage storage = new HibernateStorage();
//		final int id = storage.add(new Client(-1, "hibenate", null));
//		final Client user = storage.get(id);
//		assertEquals(id, user.getId());
//		assertEquals(id, storage.findByLogin("hibenate").getId());
//		storage.delete(id);
//		assertNull(storage.get(id));
//		storage.close();
//	}
//}