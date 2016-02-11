package ru.multithreadingExample.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	// Класс AtomicInteger создает атомарность
	private AtomicInteger amount = new AtomicInteger(0);

	public int increase() {
		// Инициализация инкремента
		return amount.incrementAndGet();
	}
}
