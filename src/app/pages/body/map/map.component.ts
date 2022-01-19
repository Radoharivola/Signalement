import { Component, OnInit } from '@angular/core';
import Map from 'ol/Map';
import Tile from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import View from 'ol/View';
import { fromLonLat } from 'ol/proj';
import { Vector } from 'ol/layer';
import { Vector as sourceVector } from 'ol/source';
import { Point } from 'ol/geom';
import { Feature, Overlay } from 'ol';
import { SignalementServiceService } from './signalement-service.service';
import Select from 'ol/interaction/Select';
import { Affectation } from './Affectation';
// import { map } from 'rxjs';
@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  map: any;
  selectSingleClick = new Select();
  popup = false;
  details: any;
  regions: any;
  affectation = new Affectation();
  constructor(private signalementService: SignalementServiceService) { }

  ngOnInit(): void {
    this.initMap();
    this.map.on('singleclick', (event: any) => {
      var f = new Vector();
      f = this.map.forEachFeatureAtPixel(
        event.pixel,
        function (_ft: any, layer: any) { return layer; }
      );
      if (f != null) {
        this.loadSignalementDetails(Number(f.get('customAttributes')));
      }
    })
    this.loadSignalements();
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
        center: fromLonLat([47.4424745, -18.887503]),
        zoom: 6.47
      })
    });
  }
  loadSignalements() {
    this.signalementService.getNASignalement().subscribe((data: any) => {
      for (let i = 0; i < data.length; i++) {
        let layer = new Vector({
          source: new sourceVector({
            features: [
              new Feature({
                geometry: new Point(fromLonLat([data[i].longitude, data[i].latitude]))
              })
            ]
          })
        });
        layer.set('customAttributes', data[i].id);
        this.map.addLayer(layer);
      }
    });
  }
  loadSignalementDetails(id: any) {
    this.signalementService.getSignalementById(id).subscribe((data: any) => {
      this.details = data[0];
      this.loadRegions();
      this.popup = true;
      console.log(this.details);
    });
  }
  loadRegions() {
    this.signalementService.getRegions().subscribe((data: any) => {
      this.regions = data;
      console.log(this.regions);
    });
  }
  affectRegion() {
    this.affectation.idSignalement=this.details.id;
    console.log(this.affectation);
    this.signalementService.affectRegion(this.affectation).subscribe(data => {
      console.log(data)
    });
    window.location.reload();
  }

}
