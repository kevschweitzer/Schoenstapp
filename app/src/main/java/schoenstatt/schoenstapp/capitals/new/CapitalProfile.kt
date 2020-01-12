package schoenstatt.schoenstapp.capitals.new

import java.io.Serializable

data class CapitalProfile(var id: String = "", var name: String = "", var password:String = "", var capitals: Int = 0, var ownerId: String = ""): Serializable