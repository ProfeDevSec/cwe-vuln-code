import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TransferService } from './transfer.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html'
})
export class TransferComponent implements OnInit {

  transferRequest = {
    fromAccount: '',
    toAccount: '',
    amount: 0
  };

  csrfToken: string;

  constructor(private transferService: TransferService, private http: HttpClient) { }

  ngOnInit() {
    this.loadCsrfToken();
  }

  loadCsrfToken() {
    this.http.get('http://localhost:8080/csrf').subscribe(
      (data: any) => {
        this.csrfToken = data.token;
      },
      error => {
        console.error('Failed to load CSRF token', error);
      }
    );
  }

  createTransfer() {
    this.transferService.createTransfer(this.transferRequest, this.csrfToken).subscribe(
      response => {
        console.log('Transfer successful', response);
      },
      error => {
        console.error('Transfer failed', error);
      }
    );
  }
}
