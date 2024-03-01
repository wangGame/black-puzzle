package kw.black.group;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

public class GameBoradGroup extends Group {
    private Array<BroadItempGroup> broadItempGroups;
    public GameBoradGroup(){
        setSize(700,700);
        broadItempGroups = new Array<>();
    }

    public void init(){
        for (int i = 0; i < 10; i++) {
            for (int i1 = 0; i1 < 10; i1++) {
                BroadItempGroup image = new BroadItempGroup(i1,i);
                image.setName(i+"-"+i1);
                addActor(image);
                broadItempGroups.add(image);
            }
        }
    }

    public BroadItempGroup check(Vector2 vector2) {
        for (BroadItempGroup broadItempGroup : broadItempGroups) {
            float x = broadItempGroup.getX();
            float y = broadItempGroup.getY();
            if (vector2.x > x && vector2.x<x+broadItempGroup.getWidth()) {
                if (vector2.y>y && vector2.y < y+broadItempGroup.getHeight()){
                    if (broadItempGroup.isBusy())
                        return null;
                    return broadItempGroup;
                }
            }
        }
        return null;
    }

    public void reset(){
        for (BroadItempGroup broadItempGroup : broadItempGroups) {
            broadItempGroup.reset();
        }
    }

    public void success() {
        hangsuccess();
        lieSuccess();
    }

    private void lieSuccess() {
        Array<Integer> lie = new Array<>();
        for (int i = 0; i < 10; i++) {
            lie.add(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BroadItempGroup actor = findActor(i1 + "-" + i);
                if (!actor.isBusy()) {
                    lie.removeIndex(lie.size-1);
                    break;
                }
            }
        }
        for (int i = 0; i < lie.size; i++) {
            Integer integer = lie.get(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BroadItempGroup actor = findActor(i1 + "-" + integer);
                actor.setEmpty();
            }
        }
    }

    private void hangsuccess() {
        Array<Integer> lie = new Array<>();
        for (int i = 0; i < 10; i++) {
            lie.add(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BroadItempGroup actor = findActor(i + "-" + i1);
                if (!actor.isBusy()) {
                    lie.removeIndex(lie.size-1);
                    break;
                }
            }
        }
        for (int i = 0; i < lie.size; i++) {
            Integer integer = lie.get(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BroadItempGroup actor = findActor(integer + "-" + i1);
                actor.setEmpty();
            }
        }
    }
}
