package ui.inaction;

import domain.objects.KuvidObject;
import domain.objects.ShootableListener;
import ui.frame.SkyPanel;

public abstract class ShootableInAction extends ObjectInAction implements ShootableListener {

	public ShootableInAction(SkyPanel sp, KuvidObject obj) {
		super(sp, obj);

	}

}
