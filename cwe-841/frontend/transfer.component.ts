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
    this.transferService.isAccountEligible(this.transferRequest.fromAccount).subscribe(
      eligible => {
        if (eligible) {
          this.transferService.createTransfer(this.transferRequest).subscribe(
            response => {
              console.log('Transfer successful', response);
            },
            error => {
              console.error('Transfer failed', error);
            }
          );
        } else {
          console.error('Account is not eligible for transfer');
        }
      },
      error => {
        console.error('Failed to check account eligibility', error);
      }
    );
  }
}
