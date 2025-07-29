
import { Injectable } from "@angular/core";
import { Product } from "./product.model";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class PanierService {
  // Le panier est un tableau de produits, avec leur quantité
  private readonly panierSubject = new BehaviorSubject<Product[]>([]);
  public readonly panier$ = this.panierSubject.asObservable(); // Observable qui permet de s'abonner

  addToPanier(product: Product) {
    const panier = [...this.panierSubject.value]; // Copie de l'état actuel du panier


    const existingProduct = panier.find((p) => p.id === product.id);

    if (existingProduct) {

      existingProduct.quantity++;
    } else {

      panier.push({ ...product, quantity: 1 });
    }


    this.panierSubject.next(panier);
  }

  removeFromPanier(productId: number) {
    const panier = this.panierSubject.value.filter((p) => p.id !== productId);
    this.panierSubject.next(panier);
  }

  getTotalQuantity(): number {
    return this.panierSubject.value.reduce((total, product) => total + product.quantity, 0);
  }

  clearPanier() {
    this.panierSubject.next([]);
  }

  getProductQuantity(product: Product) {
    return 0;
  }

  addToCart(product: Product) {
    
  }
}
