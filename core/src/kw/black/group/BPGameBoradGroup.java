package kw.black.group;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class BPGameBoradGroup extends Group {
    private Array<BPBroadItempGroup> broadItempGroups;
    public BPGameBoradGroup(){
        setSize(700,700);
        broadItempGroups = new Array<>();
    }

    public void init(){
        for (int i = 0; i < 10; i++) {
            for (int i1 = 0; i1 < 10; i1++) {
                BPBroadItempGroup image = new BPBroadItempGroup(i1,i);
                image.setName(i+"-"+i1);
                addActor(image);
                broadItempGroups.add(image);
            }
        }
    }

    public BPBroadItempGroup check(Vector2 vector2) {
        for (BPBroadItempGroup broadItempGroup : broadItempGroups) {
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
        for (BPBroadItempGroup broadItempGroup : broadItempGroups) {
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

    public void checkCanDown(){

    }

    private Vector2 tempV2 = Vector2.Zero;
    public void check(BPKuaiGroup bpKuaiGroup) {
        Array<Image> images = bpKuaiGroup.getImages();
        for (int i = 0; i < 10; i++) {
            for (int i1 = 0; i1 < 10; i1++) {
                //»ù´¡Î»ÖÃ
                BPBroadItempGroup actor = findActor(i + "-" + i1);
                if (!actor.isBusy()) {
                    float baseX = actor.getX();
                    float baseY = actor.getY();
                    boolean re = true;
                    for (Image image : images) {
                        float targetX = image.getX();
                        float targetY = image.getY();
                        tempV2.set(targetX+baseX,targetY+baseY);
                        if (check(tempV2)==null) {
                            re = false;
                            break;
                        }
                    }
                    if (re){
                        bpKuaiGroup.value();
                        return;
                    }
                }
            }
        }
        bpKuaiGroup.noValue();
    }
}
