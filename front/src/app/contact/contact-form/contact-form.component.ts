import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";

@Component({
  selector: "app-contact-form",
  templateUrl: "./contact-form.component.html",
  styleUrls: ["./contact-form.component.scss"],
})
export class ContactFormComponent {
  email: string = "";
  message: string = "";
  successMessage: string = "";

  onSubmit() {
    if (this.email && this.message && this.message.length <= 300) {
      this.successMessage = "Demande de contact envoyée avec succès";
    } else {
      this.successMessage = "Veuillez remplir tous les champs correctement";
    }
  }
}
