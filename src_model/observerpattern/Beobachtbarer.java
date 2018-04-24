package observerpattern;

import java.util.ArrayList;
import java.util.List;

public class Beobachtbarer
{
	private List<Beobachter> beobachter = new ArrayList<>();

	public void addBeobachter(Beobachter beobachter)
	{
		this.beobachter.add(beobachter);
	}

	public void informiereBeobachter()
	{
		for (Beobachter einzelnerBeobachter : beobachter)
		{
			einzelnerBeobachter.update();
		}
	}
}
