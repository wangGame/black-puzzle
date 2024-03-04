package kw.black.group;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import kw.black.MyClickListener;
import kw.black.data.PzLevelData;

public class BPBottomGroup extends Group {
    private BPGameBoradGroup bPGameBoradGroup;
    private PzLevelData data;
    private Array<BPKuaiGroup> kuaiGroupArray = new Array<>();

    public BPBottomGroup(BPGameBoradGroup bPGameBoradGroup){
        setSize(700,150);
        this.bPGameBoradGroup = bPGameBoradGroup;
        this.data = new PzLevelData();
        Table table = new Table();
        setBottomItem(table);
        addActor(table);

    }


    private void setBottomItem(Table table){
        table.clear();
        kuaiGroupArray.clear();
        Array<int[][]> floats = data.shapeData();
        for (int i = 0; i < 3; i++) {
            Group group = new Group();
            group.setSize(150,150);
            table.add(group).padLeft(50).padRight(50);
            Array<int[][]> temps = new Array(floats);
            int[][] floats1 = temps.removeIndex((int) (Math.random() * temps.size));
            BPKuaiGroup kuaiGroup = new BPKuaiGroup(floats1);
            group.addActor(kuaiGroup);
            kuaiGroupArray.add(kuaiGroup);
            kuaiGroup.setPosition(75,75, Align.center);
            float i1 = 150.0f / (70.0f * 5);
            kuaiGroup.setOrigin(Align.center);
            kuaiGroup.setScale(i1);
            new MyClickListener(kuaiGroup){
                private Array<BPBroadItempGroup> tempArrays = new Array<>();
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    getKuaiGroup().setScale(1.0f);
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    super.touchDragged(event, x, y, pointer);
                    BPKuaiGroup kuaiGroup1 = getKuaiGroup();
                    Group blackGroup = kuaiGroup1.getBlackGroup();
                    blackGroup.setDebug(true);
                    blackGroup.setPosition(x,y + 30,Align.bottom);
                    Array<Image> images = kuaiGroup1.getImages();
                    bPGameBoradGroup.reset();
                    tempArrays.clear();
                    for (Image image : images) {
                        float x1 = image.getX()+10;
                        float y1 = image.getY();
                        Vector2 vector2 = new Vector2(x1,y1);
                        image.getParent().localToStageCoordinates(vector2);
                        bPGameBoradGroup.stageToLocalCoordinates(vector2);
                        BPBroadItempGroup check = bPGameBoradGroup.check(vector2);
                        if (check==null){
                            tempArrays.clear();
                            break;
                        }else {
                            tempArrays.add(check);
                        }
                    }
                    if (tempArrays.size>0){
                        for (BPBroadItempGroup tempArray : tempArrays) {
                            tempArray.light();
                        }
                    }
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    if (tempArrays.size>0) {
                        for (BPBroadItempGroup tempArray : tempArrays) {
                            tempArray.setFill();
                        }
                        kuaiGroupArray.removeValue(getKuaiGroup(),false);
                        getKuaiGroup().remove();
                        if (kuaiGroupArray.size<=0) {
                            setBottomItem(table);
                        }
                    }else {
                        getKuaiGroup().setScale(150.0f / (70.0f * 5));
                        getKuaiGroup().resetPosition();
                    }
                    //检测
                    bPGameBoradGroup.success();
                    //那些是放不了的
                    canDown();
                    //失败
                    fail();
                }
            };
        }
        table.pack();
        setX(350,Align.center);
    }

    private boolean fail() {
        for (BPKuaiGroup bpKuaiGroup : kuaiGroupArray) {
            if (bpKuaiGroup.isValue()) {
                return false;
            }
        }
        System.out.println("失败");
        return true;
    }

    public void canDown(){
        for (BPKuaiGroup bpKuaiGroup : kuaiGroupArray) {
            bPGameBoradGroup.check(bpKuaiGroup);
        }
    }
}
