package kw.black.screen;

import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.screen.BaseScreen;

import kw.black.group.GameBoradGroup;
import kw.black.group.BottomGroup;

public class GameScreen extends BaseScreen {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        GameBoradGroup gameBoradGroup = new GameBoradGroup();
        addActor(gameBoradGroup);
        gameBoradGroup.init();
        gameBoradGroup.setPosition(360,640, Align.center);

        BottomGroup bottomGroup = new BottomGroup(gameBoradGroup);
        addActor(bottomGroup);
        bottomGroup.setY(gameBoradGroup.getY()-50,Align.top);
        bottomGroup.setX(360,Align.center);
    }
}
