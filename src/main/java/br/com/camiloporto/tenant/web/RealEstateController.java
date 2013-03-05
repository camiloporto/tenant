package br.com.camiloporto.tenant.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

import br.com.camiloporto.tenant.model.Imovel;
import br.com.camiloporto.tenant.model.ImovelMedia;
import br.com.camiloporto.tenant.search.MediaElasticSearchRestRepository;
import br.com.camiloporto.tenant.service.ImovelService;

@RequestMapping("/realestates")
@Controller
public class RealEstateController {
	
	private static final String JPEG = "jpg";

	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private MediaElasticSearchRestRepository mediaRepository;
	
	@Autowired
	private Cloudinary cloudinary;

	//TODO configurar tamanho dinamicamente
	private int mediaWidth = 430;

	//TODO configurar tamanho dinamicamente (@media query css??)
	private int mediaHeight = 300;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("realestate/index");
		List<Imovel> imoveis = imovelService.findAllSortedByUltimaAtualizacao();
		mav.addObject("imoveis", imoveis);
		
		return mav;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("realestate/detail");
		Imovel saved = imovelService.findImovel(id);
		List<String> medias = fetchImovelMedias(saved);
		mav.addObject("imovel", saved);
		mav.addObject("medias", medias);
		
		return mav;
	}
	
	private List<String> fetchImovelMedias(Imovel saved) {
		List<String> result = Collections.EMPTY_LIST;
		if(saved != null && saved.getId() != null) {
			try {
				List<ImovelMedia> medias = mediaRepository.findByImovelId(saved.getId());
				result = createMediaURLs(medias);
			} catch (Exception e) {
					//TODO tratar excecao
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}

	private List<String> createMediaURLs(List<ImovelMedia> medias) {
		List<String> result = new ArrayList<String>();
		for (ImovelMedia imovelMedia : medias) {
			Transformation t = new Transformation();
			t.height(mediaHeight).crop("scale");
			String url = cloudinary.url()
					.format(JPEG)
					.transformation(t).
					generate(imovelMedia.getId());
			result.add(url);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, params="q")
	public ModelAndView search(@RequestParam String q) {
		if(q == null || "".equals(q)) {
			return home();
		}
		ModelAndView mav = new ModelAndView("realestate/index");
		List<Imovel> imoveis = imovelService.genericQuery(q);
		mav.addObject("imoveis", imoveis);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HashMap<String,String> index(Imovel i) {
		Imovel indexed = imovelService.saveImovel(i);
		HashMap<String, String> jsonResponseMap = new HashMap<String, String>();
		jsonResponseMap.put("status", "ok");
		jsonResponseMap.put("id", indexed.getId());
		return jsonResponseMap;
	}

}
