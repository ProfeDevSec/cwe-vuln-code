import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ImmutableTransferRequest } from './immutable-transfer-request.model';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  private apiUrl = 'http://localhost:8080/transfers';

  constructor(private http: HttpClient) { }

  createTransfer(transferRequest: ImmutableTransferRequest): Observable<any> {
    // Utiliza una clase inmutable para TransferRequest
    const payload = {
      fromAccount: transferRequest.getFromAccount(),
      toAccount: transferRequest.getToAccount(),
      amount: transferRequest.getAmount()
    };
    return this.http.post(`${this.apiUrl}/create`, payload);
  }
}
