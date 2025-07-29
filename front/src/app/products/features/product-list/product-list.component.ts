
import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model"; // Modèle de produit
import { ProductsService } from "app/products/data-access/products.service"; // Service pour récupérer les produits
import { PanierService } from "app/products/data-access/panier.service"; // Service pour gérer le panier
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component"; // Formulaire de produit
import { ButtonModule } from "primeng/button"; // Module PrimeNG pour les boutons
import { CardModule } from "primeng/card"; // Module PrimeNG pour les cartes
import { DataViewModule } from 'primeng/dataview'; // Module PrimeNG pour la vue des données
import { DialogModule } from 'primeng/dialog'; // Module PrimeNG pour les dialogues

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService); // Injection du service de produits
  private readonly panierService = inject(PanierService); // Injection du service de gestion du panier

  public readonly products = this.productsService.products; // Liste des produits

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct); // Produit à éditer (signal)

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }


  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }


  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }


  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe(); // Création
    } else {
      this.productsService.update(product).subscribe(); // Mise à jour
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }


  private closeDialog() {
    this.isDialogVisible = false;
  }


  public onAddToCart(product: Product) {
    this.panierService.addToCart(product);
  }


  public getQuantityInCart(product: Product): number {
    return this.panierService.getProductQuantity(product);
  }
}
