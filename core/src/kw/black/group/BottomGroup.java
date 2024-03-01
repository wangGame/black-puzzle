package kw.black.group;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import kw.black.MyClickListener;
import kw.black.data.LevelData;

public class BottomGroup extends Group {
    private GameBoradGroup gameBoradGroup;
    private LevelData data;
    public BottomGroup(GameBoradGroup gameBoradGroup){
        setSize(700,150);
        this.gameBoradGroup = gameBoradGroup;
        this.data = new LevelData();
        Table table = new Table();
        setBottomItem(table);
        addActor(table);

    }


    private Array<KuaiGroup> kuaiGroupArray = new Array<>();
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
            KuaiGroup kuaiGroup = new KuaiGroup(floats1);
            group.addActor(kuaiGroup);
            kuaiGroupArray.add(kuaiGroup);
            kuaiGroup.setPosition(75,75, Align.center);
            float i1 = 150.0f / (70.0f * 5);
            kuaiGroup.setOrigin(Align.center);
            kuaiGroup.setScale(i1);
            new MyClickListener(kuaiGroup){
                private Array<BroadItempGroup> tempArrays = new Array<>();
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    getKuaiGroup().setScale(1.0f);
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    super.touchDragged(event, x, y, pointer);
                    KuaiGroup kuaiGroup1 = getKuaiGroup();
                    Group blackGroup = kuaiGroup1.getBlackGroup();
                    blackGroup.setPosition(x,y);
                    Array<Image> images = kuaiGroup1.getImages();
                    gameBoradGroup.reset();
                    tempArrays.clear();
                    for (Image image : images) {
                        float x1 = image.getX()+10;
                        float y1 = image.getY();
                        Vector2 vector2 = new Vector2(x1,y1);
                        image.getParent().localToStageCoordinates(vector2);
                        gameBoradGroup.stageToLocalCoordinates(vector2);
                        BroadItempGroup check = gameBoradGroup.check(vector2);
                        if (check==null){
                            tempArrays.clear();
                            break;
                        }else {
                            tempArrays.add(check);
                        }
                    }
                    if (tempArrays.size>0){
                        for (BroadItempGroup tempArray : tempArrays) {
                            tempArray.light();
                        }
                    }
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                    if (tempArrays.size>0) {
                        for (BroadItempGroup tempArray : tempArrays) {
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
                    //¼ì²â
                    gameBoradGroup.success();
                }
            };
        }
        table.pack();
        setX(350,Align.center);
    }
}
