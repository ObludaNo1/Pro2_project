package cz.uhk.pro2.flappy.game;

// rozhran� pro objekty, ktere potrebuji vedet, kolik casu/tick� ub�hlo od za��tku hry

public interface TickAware {
	
//	zmeni stav herni entity s ohledem na zmenu herniho casu
//	ticksSinceStart cas od zahajeni hry
	
	void tick(long ticksSinceStart);
	
}
