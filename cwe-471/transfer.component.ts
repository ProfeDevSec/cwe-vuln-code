import { Component } from '@angular/core';
import { TransferService } from './transfer.service';
import { ImmutableTransferRequest } from './immutable-transfer-request.model';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html'
})
export class TransferComponent {

  fromAccount: string = '';
  toAccount: string = '';
  amount: number = 0;

  constructor(private transferService: TransferService) {}

  createTransfer() {
    // Crear una instancia inmutable de TransferRequest
    const transferRequest = new ImmutableTransferRequest(this.fromAccount, this.toAccount, this.amount);
    this.transferService.createTransfer(transferRequest).subscribe(
      response => {
        console.log('Transfer successful', response);
      },
      error => {
        console.error('Transfer failed', error);
      }
    );
  }
}
