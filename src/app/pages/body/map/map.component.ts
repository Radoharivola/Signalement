import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import Tile from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import {Point} from 'ol/geom'
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  map: any;
  constructor() { }

  ngOnInit(): void {
    this.initMap();
    // const iconFeature = new Feature({
    //   geometry: new Point(fromLonLat([47.4424745, -18.887503])),
    //   name: 'Somewhere near Nottingham',
    // });
  }
  initMap(): void {
    this.map = new Map({
      target: 'map',
      layers: [
        new Tile({
          source: new OSM()
        })
      ],
      view: new View({
        center: fromLonLat([47.4424745,-18.887503]),
        zoom: 6.47
      })
    });
  }
}
