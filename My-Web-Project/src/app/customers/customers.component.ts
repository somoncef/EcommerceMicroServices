import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customers',
  standalone: false,

  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent {
  customers: any;

  constructor(private http: HttpClient,private router:Router) {}

  ngOnInit() {
    this.http.get('http://localhost:8888/customer-service/api/customers').subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des produits:', err);
      }
    });
  }

  getBills(c: any) {
    this.router.navigate(['/bills/'+ c.id]);

  }
}
