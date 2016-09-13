package ru.poker.Interfaces;

import ru.poker.Classes.Cart;
import ru.poker.Classes.Gamer;

public interface IGenerator {
	Cart[] generateDeck();

	Gamer[] generateGamers();
}
