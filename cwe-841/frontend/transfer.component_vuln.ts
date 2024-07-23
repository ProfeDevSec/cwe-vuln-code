import { Component } from '@angular/core';
import { TransferService } from './transfer.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html'
})
export class TransferComponent {

  transferRequest = {
    fromAccount: '',
    toAccount: '',
    amount: 0
  };

  constructor(private transferService: TransferService) { }

  createTransfer() {
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
