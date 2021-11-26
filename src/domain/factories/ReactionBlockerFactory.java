package domain.factories;

import domain.catalog.Catalog;
import domain.catalog.rb.RbDescription;
import domain.hitbox.HitBoxCircle;
import domain.kuvidgame.KuVidGame;
import domain.objects.reactionblocker.Rb;

public class ReactionBlockerFactory implements ObjectFactory<Rb> {

	private static ReactionBlockerFactory rbFactory = null;
	private FactoryListener factoryListener;

	public static ReactionBlockerFactory getInstance() {
		if (rbFactory == null) {
			rbFactory = new ReactionBlockerFactory();
		}
		return rbFactory;
	}

	@Override
	public Rb createObject(ObjectType type) {
		return createReactionBlocker(type);
	}

	@Override
	public void setFactoryListener(FactoryListener fl) {
		factoryListener = fl;
	}

	public FactoryListener getFactoryListener() {
		return factoryListener;
	}

	private Rb createReactionBlocker(ObjectType type) {
		Rb rb = createReactionBlockerWithoutAddingToPanel(type);
		getFactoryListener().addRbInActionToSkyPanel(rb);
		return rb;
	}

	public Rb createReactionBlockerWithoutAddingToPanel(ObjectType type) {
		Catalog gameCatalog = KuVidGame.getInstance().getCatalog();
		RbDescription rbDescr = gameCatalog.getRbDescription(type);
		return createReactionBlockerWithDescription(rbDescr);

	}

	private Rb createReactionBlockerWithDescription(RbDescription rbDescr) {
		int diameter = rbDescr.getDiameter();
		Rb rb = new Rb(rbDescr, new HitBoxCircle(diameter));
		return rb;

	}

}
