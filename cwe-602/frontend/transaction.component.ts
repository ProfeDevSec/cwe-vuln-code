import { Component } from '@angular/core';
import { TransactionService } from './transaction.service';
import { Transaction } from './transaction.model';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent {

  transaction: Transaction = new Transaction();
  message: string;

  constructor(private transactionService: TransactionService) { }

  createTransaction() {
    this.transactionService.createTransaction(this.transaction).subscribe(response => {
      this.message = response;
    }, error => {
      this.message = "Error creating transaction";
    });
  }
}
