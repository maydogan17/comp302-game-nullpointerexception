package ui.inaction;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import domain.objects.KuvidObject;
import ui.Resize;
import ui.frame.SkyPanel;

public abstract class ObjectInAction implements Drawable{
	private SkyPanel superPanel;
	private KuvidObject object;
	private BufferedImage objectImage;
	
	public ObjectInAction(SkyPanel sp, KuvidObject obj) {
		this.setSuperPanel(sp);
		this.setObject(obj);

		initImage();

	}
	
	public void initImage() {
		try {
			objectImage = ImageIO.read(new File(getObject().getObjDescr().getImageDirectory()));
			objectImage = Resize.resize(objectImage, getObject().getImageSize().getWidth(), getObject().getImageSize().getHeight());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics g) {

		g.drawImage(getObjectImage(), getObject().getImagePosition().getX(), getObject().getImagePosition().getY(),
				getSuperPanel());

	}

	public SkyPanel getSuperPanel() {
		return superPanel;
	}

	public void setSuperPanel(SkyPanel superPanel) {
		this.superPanel = superPanel;
	}

	public KuvidObject getObject() {
		return object;
	}

	public void setObject(KuvidObject object) {
		this.object = object;
	}

	public BufferedImage getObjectImage() {
		return objectImage;
	}

	public void setObjectImage(BufferedImage objectImage) {
		this.objectImage = objectImage;
	}
}
