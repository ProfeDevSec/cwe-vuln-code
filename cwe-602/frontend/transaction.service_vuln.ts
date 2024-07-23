import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './transaction.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) { }

  createTransaction(transaction: Transaction, hasPermission: boolean): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/create?hasPermission=${hasPermission}`, transaction);
  }
}
