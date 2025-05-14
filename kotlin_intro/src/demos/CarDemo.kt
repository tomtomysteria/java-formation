package demos

import models.car.*

object CarDemo {
  fun run() {
    println("=== Démo des voitures ===")
    val car = Car("Toyota", 2018)
    car.displayInfo()
  }
}
