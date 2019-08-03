package schoenstatt.schoenstapp.home

class MainPresenter(private val model: MainModel) {
    fun logOut() {
        model.logOut()
    }
}