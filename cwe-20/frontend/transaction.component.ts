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
    if (this.validateInput(this.transaction)) {
      this.transactionService.createTransaction(this.transaction).subscribe(response => {
        this.message = response;
      }, error => {
        this.message = "Error creating transaction";
      });
    } else {
      this.message = "Invalid input";
    }
  }

  validateInput(transaction: Transaction): boolean {
    const accountNumberPattern = /^\d{10}$/;
    if (!accountNumberPattern.test(transaction.accountNumber)) {
      return false;
    }
    if (transaction.amount <= 0) {
      return false;
    }
    return true;
  }
}
