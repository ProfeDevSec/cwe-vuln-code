import { Component } from '@angular/core';
import { TransferService } from './transfer.service';
import { TransferRequest } from './transfer-request.model';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html'
})
export class TransferComponent {

  transferRequest: TransferRequest;

  constructor(private transferService: TransferService) {
    this.transferRequest = new TransferRequest('', '', 0);
  }

  createTransfer() {
    // El objeto transferRequest puede ser modificado después de ser creado
    this.transferService.createTransfer(this.transferRequest).subscribe(
      response => {
        console.log('Transfer successful', response);
      },
      error => {
        console.error('Transfer failed', error);
      }
    );
  }
}
