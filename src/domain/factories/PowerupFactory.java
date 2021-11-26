package domain.factories;

import domain.catalog.Catalog;
import domain.catalog.powerup.PowerupDescription;
import domain.hitbox.HitBoxCircle;
import domain.kuvidgame.KuVidGame;
import domain.objects.powerups.Powerup;

public class PowerupFactory implements ObjectFactory<Powerup> {

	private static PowerupFactory powerupFactory = null;
	private FactoryListener factoryListener;

	public static PowerupFactory getInstance() {
		if (powerupFactory == null) {
			powerupFactory = new PowerupFactory();
		}
		return powerupFactory;
	}

	@Override
	public Powerup createObject(ObjectType type) {
		return createPowerup(type);
	}

	@Override
	public void setFactoryListener(FactoryListener fl) {
		factoryListener = fl;
	}

	public FactoryListener getFactoryListener() {
		return factoryListener;
	}

	private Powerup createPowerup(ObjectType type) {
		Catalog gameCatalog = KuVidGame.getInstance().getCatalog();
		PowerupDescription powDescr = gameCatalog.getPowerupDescription(type);
		int diameter = powDescr.getDiameter();
		Powerup pow = new Powerup(powDescr, new HitBoxCircle(diameter));
		getFactoryListener().addShootableInActionToSkyPanel(pow);
		return pow;
	}

}
