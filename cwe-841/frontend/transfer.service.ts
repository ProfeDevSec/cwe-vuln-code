import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private apiUrl = 'http://localhost:8080/transfers';

  constructor(private http: HttpClient) { }

  createTransfer(transferRequest: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, transferRequest);
  }

  isAccountEligible(accountNumber: string): Observable<boolean> {
    return this.http.get<boolean>(`http://localhost:8080/accounts/${accountNumber}/eligible`);
  }
}
