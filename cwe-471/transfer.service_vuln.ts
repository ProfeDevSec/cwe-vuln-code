import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransferRequest } from './transfer-request.model';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private apiUrl = 'http://localhost:8080/transfers';

  constructor(private http: HttpClient) { }

  createTransfer(transferRequest: TransferRequest): Observable<any> {
    // Asume que transferRequest es inmutable, pero puede ser modificado
    return this.http.post(`${this.apiUrl}/create`, transferRequest);
  }
}
