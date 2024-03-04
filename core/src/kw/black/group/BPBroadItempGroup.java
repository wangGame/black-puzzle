package kw.black.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

public class BPBroadItempGroup extends Group {
    public Image blackBg;
    public Image blackImg;
    private float cellWidth;
    private boolean busy;
    public BPBroadItempGroup(int x, int y){
        cellWidth = 700.0f / 10;
        blackBg = new Image(
                new NinePatch(Asset.getAsset().getTexture("white.png"),
                        3,3,3,3));
        blackBg.setSize(cellWidth-2,cellWidth-2);
        addActor(blackBg);
        setPosition( x*cellWidth,y*cellWidth, Align.center);
        setSize(blackBg.getWidth(), blackBg.getHeight());
        blackImg = new Image(new NinePatch(
                Asset.getAsset().getTexture("white.png"),3,3,3,3));
        blackImg.setColor(Color.WHITE);
        addActor(blackImg);
        blackImg.setSize(cellWidth-2,cellWidth-2);
        blackImg.setPosition(getWidth()/2.f,getHeight()/2.0f,Align.center);
    }

    public void light() {
        if (busy)return;
        blackImg.setColor(Color.valueOf("#999999"));
    }

    public void reset(){
        if (busy)return;
        blackImg.setColor(Color.WHITE);
    }

    public void setFill() {
        blackImg.setColor(Color.BROWN);
        this.busy = true;
    }

    public void setEmpty(){
        blackImg.addAction(Actions.color(Color.WHITE,0.2f));
        this.busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return "BroadItempGroup{" +
                "blackBg=" + blackBg +
                ", blackImg=" + blackImg +
                ", cellWidth=" + cellWidth +
                ", busy=" + busy +
                '}';
    }
}
