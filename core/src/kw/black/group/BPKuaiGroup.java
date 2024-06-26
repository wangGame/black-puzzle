package kw.black.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.listener.OrdinaryButtonListener;

public class BPKuaiGroup extends Group {
    private Array<Image> images;
    private int [][] data;
    private Group blackGroup;
    private boolean value;

    public BPKuaiGroup(int[][] floats1){
        setSize(70*5,70*5);
        value = true;
        this.images = new Array<>();
        this.blackGroup = new Group();
        blackGroup.setSize(getWidth(),getHeight());
        addActor(blackGroup);
        data = floats1;

        Vector2 startV2 = new Vector2();
        Vector2 endV2 = new Vector2();
        startV2.set(Integer.MAX_VALUE,Integer.MAX_VALUE);
        endV2.set(Integer.MIN_VALUE,Integer.MIN_VALUE);
        for (int i = 0; i < data.length; i++) {
            for (int i1 = 0; i1 < data[i].length; i1++) {
                if (data[i][i1] != 0) {
                    Image image = new Image(Asset.getAsset().getTexture("white.png"));

                    image.setPosition(i * 70.0f, i1 * 70);
                    blackGroup.addActor(image);
                    image.setColor(Color.RED);
                    images.add(image);
                    startV2.x = Math.min(image.getX(),startV2.x);
                    startV2.y = Math.min(image.getY(),startV2.y);
                    endV2.x = Math.max(image.getX(Align.right),endV2.x);
                    endV2.y = Math.max(image.getY(Align.top),endV2.y);
                }
            }
        }
        blackGroup.setSize(endV2.x - startV2.x,endV2.y - startV2.y);
        blackGroup.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
    }

    public Group getBlackGroup() {
        return blackGroup;
    }

    public Array<Image> getImages() {
        return images;
    }

    public void resetPosition() {
        blackGroup.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
    }

    public int[][] getData() {
        return data;
    }

    public void noValue() {
        this.value = false;
        for (Image image : images) {
            image.setColor(Color.BLUE);
        }
    }

    public void value() {
        this.value = true;
        for (Image image : images) {
            image.setColor(Color.RED);
        }
    }

    public boolean isValue() {
        return value;
    }
}
