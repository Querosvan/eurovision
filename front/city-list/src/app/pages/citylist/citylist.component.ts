import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import {CitiesService} from '../../services/cities.service';
import {debounceTime, distinctUntilChanged, startWith, tap, delay} from 'rxjs/operators';
import {CitiesDataSource} from '../../services/cities.datasource';

@Component({
  selector: 'app-citylist',
  templateUrl: './citylist.component.html',
  styleUrls: ['./citylist.component.scss']
})
export class CitylistComponent implements OnInit, AfterViewInit {

  dataSource: CitiesDataSource;

  displayedColumns = ['id', 'name'];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild('input') input: ElementRef;

  constructor(private route: ActivatedRoute,
              private citiesService: CitiesService) {

  }

  ngOnInit() {
      this.dataSource = new CitiesDataSource(this.citiesService);
      this.dataSource.loadCities(0, 5);
  }

  ngAfterViewInit() {

    this.paginator.page
      .pipe(
          tap(() => this.loadCitiesPage())
      )
      .subscribe();

  }

  loadCitiesPage() {
      this.dataSource.loadCities(
          this.paginator.pageIndex,
          this.paginator.pageSize);
  }

}
