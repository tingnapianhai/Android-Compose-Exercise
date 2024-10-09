# Android Exercise

This is a small casino app. We have received feedback regarding this app,
specifically highlighting concerns about the application's quality. One small
issue identified is the misalignment of game items, particularly when game
names are excessively long. Also code quality concerns were raised.

Please address the alignment issue, test the app and look through the rest of
the code and address any other issues that you find.

Prioritize what you find most critical. If you don't have the time to do all
changes that you want, please provide a list of improvements with your
submission.

Do not change anything under `com.android.compose.exercise.fake`, that is just a fake api
to limit the scope of this exercise.
[README.md](README.md)[README.md](..%2FKai%20-%20Android%20test%2FREADME.md)[README.md](..%2FKai%20-%20Android%20test%2FREADME.md)

------------------------------------------------------------------------
# Android Exercise Submission

## Improvements
- Fix misalignment of game items
- Fix back button behaviour
- Improve for landscape mode for home view
- HomeView.kt: remove usage of runBlocking, some UI tweaks
- SearchView.kt: remove usage of GlobalScope, add loading progress indicator
- SearchField.kt: add check for text empty, add debounce for user input
- GameView.kt: add back button
- Add transition animation when open GameView (use beta version, needs to update to stable version)
- GameItem.kt: update image scale, limit max lines and make it ellipsis on end to fix for long text
- BottomNavigationBar.kt: add selected state, add click events in constructor
- HorizontalGameList.kt: add padding to align title with game items, add loading progress indicator for Home View
- MainActivity.kt: move GameView to handle in Home & Search View, some UI tweaks
- Repo.kt, add simple repo, to handle API and add some cache to improve user experience

## Future improvements if have more time
- Add unit tests
- When app gets bigger, consider of using MVVM / MVI
- Consider of app modularisation
- Consider of introducing some 3rd party libraries such as DJ, network part etc
- Improve UI/UX, especially for landscape mode
- Add network scheme, retry / cancel etc
- Add more animations
