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
  hasPermission: boolean = true; // CWE-602: Este valor puede ser modificado por un atacante

  constructor(private transactionService: TransactionService) { }

  createTransaction() {
    this.transactionService.createTransaction(this.transaction, this.hasPermission).subscribe(response => {
      this.message = response;
    }, error => {
      this.message = "Error creating transaction";
    });
  }
}
