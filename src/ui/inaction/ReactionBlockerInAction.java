package ui.inaction;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import domain.objects.KuvidObject;
import domain.objects.reactionblocker.Rb;
import domain.objects.reactionblocker.RbListener;
import ui.frame.SkyPanel;

public class ReactionBlockerInAction extends ObjectInAction implements RbListener {
	
	
	private AudioClip expClip;
	
	public ReactionBlockerInAction(SkyPanel sp, KuvidObject obj) {
		super(sp, obj);
		initializeSounds();

	}

	public void initialize(Rb rb) {
		rb.addRbListener(this);

	}

	@Override
	public void onLocationChangeEvent() {

		getSuperPanel().repaint();
	}

	@Override
	public void onRemoveEvent() {
		expClip.play();
		getSuperPanel().removeObjectInAction(this);
		getSuperPanel().getRbInActionsInPanel().remove(this);
	}

	@Override
	public List<KuvidObject> getObjectsInPanel() {
		List<KuvidObject> objects = new ArrayList<KuvidObject>();
		for (ObjectInAction objic : getSuperPanel().getObjectInActionsOnPanel()) {
			objects.add(objic.getObject());
		}
		return objects;
	}
	
	public void initializeSounds() {
		try {
			expClip = Applet.newAudioClip(
			new URL("file:sounds/exploison_sound.wav"));
			} catch (MalformedURLException murle) {
			System.out.println(murle);
			}
	}

}
