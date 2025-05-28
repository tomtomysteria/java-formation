import { Component } from '@angular/core';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-user',
  standalone: true, // Rend le composant autonome
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  name: string = 'Alice';
  age: number = 28;

  product: Product = new Product(1, 'Laptop', 1200);

  getGreeting(): string {
    return `Bonjour, je m'appelle ${this.name}, j'ai ${this.age} ans.`;
  }
}
