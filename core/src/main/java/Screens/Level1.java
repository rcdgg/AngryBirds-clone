package Screens;

public class Level1 {
    final AngryBirds game;
    private Button pause;
    SpriteBatch batch;
    Stage stage;
    public Level1(final AngryBirds game){
        this.game = game;
        Texture pausebutt = new Texture("screens/mainmenu/Screenshot 2024-10-25 at 14.13.42.png");
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        batch = game.batch;
//        Skin skin = new Skin(Gdx.files.internal("screens/mainmenu/skin/comic-ui.json"));
        pause = new Button(new TextureRegionDrawable(new TextureRegion(pausebutt)));
        pause.setPosition(50, 50);
//        pause.setTouchable(Touchable.enabled);
        stage.addActor(pause);
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Pause");
            }
        });
        stage.setDebugAll(true);
        @Override
        public void show() {
            InputMultiplexer multiplexer = new InputMultiplexer();
            multiplexer.addProcessor(this);
            multiplexer.addProcessor(stage);
            Gdx.input.setInputProcessor(multiplexer);
        }

        @Override
        public void render(float delta) {
            ScreenUtils.clear(Color.BLACK);
            game.viewport.apply();
            stage.getBatch().setProjectionMatrix(game.viewport.getCamera().combined);
            batch.begin();
            float worldWidth = game.viewport.getWorldWidth();
            float worldHeight = game.viewport.getWorldHeight();
            batch.draw(Assets.background, 0, 0, worldWidth, worldHeight);
            batch.end();
            stage.draw();
}
