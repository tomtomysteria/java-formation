package models.car

class Car(val brand: String, val year: Int) {
  fun displayInfo() {
    println("Car Brand: $brand, Year: $year")
  }
}
