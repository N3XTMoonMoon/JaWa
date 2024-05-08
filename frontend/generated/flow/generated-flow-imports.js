import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '164c725bf5067180725e52eb23ad80b1d879a9f841add4b93191eb115a822da3') {
    pending.push(import('./chunks/chunk-4d4b57885b5f51a8906be74d09e4f7183da61cfdc27430918d9ed6056d515957.js'));
  }
  if (key === '2bf4052c5cee5cd2c8835f611fac590243929604becd683f85d78182c291118e') {
    pending.push(import('./chunks/chunk-fb79006ddb76f1f17466a3815918c9a05a488bec024e9005435df9022d732772.js'));
  }
  if (key === '87038c772b25f1e883d109e7182d134d259a65a2d59eb8695a08160dcd125fac') {
    pending.push(import('./chunks/chunk-67a960f45487f171f1bda8fcac2bee60c218af9533cace68e38eed81e1223653.js'));
  }
  if (key === 'c2e109047d298cb1a9fabc5b215d42e00a8aa5ac94b012e251f69182740a5e88') {
    pending.push(import('./chunks/chunk-0c559bc07f8c1f8b5e3cb61c70ea99c4f75ff219cecccc12f56df109f084189f.js'));
  }
  if (key === 'd066794432ce417d1378ef7ae5cd473ce8821aebf328b3791974da86cb1260a7') {
    pending.push(import('./chunks/chunk-67a960f45487f171f1bda8fcac2bee60c218af9533cace68e38eed81e1223653.js'));
  }
  if (key === 'e1ffbf26e50b1fbc5e22798e4c4c31330df1aac7a7e301481d5131a4939e7113') {
    pending.push(import('./chunks/chunk-fb79006ddb76f1f17466a3815918c9a05a488bec024e9005435df9022d732772.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}