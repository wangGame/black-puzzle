package kw.black.screen;

import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.screen.BaseScreen;

import kw.black.group.BPGameBoradGroup;
import kw.black.group.BPBottomGroup;

public class BlackPuzzleScreen extends BaseScreen {
    public BlackPuzzleScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        BPGameBoradGroup bPGameBoradGroup = new BPGameBoradGroup();
        addActor(bPGameBoradGroup);
        bPGameBoradGroup.init();
        bPGameBoradGroup.setPosition(360,640, Align.center);

        BPBottomGroup bPBottomGroup = new BPBottomGroup(bPGameBoradGroup);
        addActor(bPBottomGroup);
        bPBottomGroup.setY(bPGameBoradGroup.getY()-50,Align.top);
        bPBottomGroup.setX(360,Align.center);
    }
}
