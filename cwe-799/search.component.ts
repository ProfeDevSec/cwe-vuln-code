import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { debounceTime, throttleTime } from 'rxjs/operators';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
  searchForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      query: ['']
    });

    this.searchForm.get('query').valueChanges
      .pipe(
        debounceTime(300), // Controla la frecuencia de las solicitudes
        throttleTime(1000) // Evita solicitudes demasiado frecuentes
      )
      .subscribe(query => {
        this.search(query);
      });
  }

  search(query: string): void {
    this.http.get(`/api/search?q=${query}`).subscribe(response => {
      console.log(response);
    });
  }
}
