package models.book

class EBook(private val title: String, private val author: String, private val fileSize: Double) :
        Book(title, author) {
  override fun displayInfo() {
    super.displayInfo()
    print(", Taille du fichier: $fileSize Mo")
  }
}
