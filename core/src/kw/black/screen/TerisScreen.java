package kw.black.screen;

import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.screen.BaseScreen;

import kw.black.group.TeGameBoradGroup;

public class TerisScreen extends BaseScreen {
    public TerisScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        TeGameBoradGroup group = new TeGameBoradGroup();
        addActor(group);
        group.init();
        group.setPosition(360,640, Align.center);
    }
}
