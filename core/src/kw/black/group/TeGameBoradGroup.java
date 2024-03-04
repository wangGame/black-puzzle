package kw.black.group;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class TeGameBoradGroup extends Group {
    private Array<TeBoradItemGroup> broadItempGroups;
    public TeGameBoradGroup(){
        float cellWidth = 700.0f / 14;
        setSize(cellWidth * 10,cellWidth * 20);
        broadItempGroups = new Array<>();
        setDebug(true);
    }

    public void init(){
        for (int i = 0; i < 20; i++) {
            for (int i1 = 0; i1 < 10; i1++) {
                TeBoradItemGroup image = new TeBoradItemGroup(i1,i);
                image.setName(i+"-"+i1);
                addActor(image);
                broadItempGroups.add(image);
            }
        }
    }

    public TeBoradItemGroup check(Vector2 vector2) {
        for (TeBoradItemGroup broadItempGroup : broadItempGroups) {
            float x = broadItempGroup.getX();
            float y = broadItempGroup.getY();
            if (vector2.x >= x && vector2.x<x+broadItempGroup.getWidth()) {
                if (vector2.y>=y && vector2.y < y+broadItempGroup.getHeight()){
                    if (broadItempGroup.isBusy())
                        return null;
                    return broadItempGroup;
                }
            }
        }
        return null;
    }

    public void reset(){
        for (TeBoradItemGroup broadItempGroup : broadItempGroups) {
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
                BPBroadItempGroup actor = findActor(i1 + "-" + i);
                if (!actor.isBusy()) {
                    lie.removeIndex(lie.size-1);
                    break;
                }
            }
        }
        for (int i = 0; i < lie.size; i++) {
            Integer integer = lie.get(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BPBroadItempGroup actor = findActor(i1 + "-" + integer);
                actor.setEmpty();
            }
        }
    }

    private void hangsuccess() {
        Array<Integer> lie = new Array<>();
        for (int i = 0; i < 10; i++) {
            lie.add(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BPBroadItempGroup actor = findActor(i + "-" + i1);
                if (!actor.isBusy()) {
                    lie.removeIndex(lie.size-1);
                    break;
                }
            }
        }
        for (int i = 0; i < lie.size; i++) {
            Integer integer = lie.get(i);
            for (int i1 = 0; i1 < 10; i1++) {
                BPBroadItempGroup actor = findActor(integer + "-" + i1);
                actor.setEmpty();
            }
        }
    }

    public void outBlack(){

    }
}
