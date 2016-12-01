package cz.uhk.pro2.flappy.game;

// rozhraní pro objekty, ktere potrebuji vedet, kolik casu/tickù ubìhlo od zaèátku hry

public interface TickAware {
	
//	zmeni stav herni entity s ohledem na zmenu herniho casu
//	ticksSinceStart cas od zahajeni hry
	
	void tick(long ticksSinceStart);
	
}
