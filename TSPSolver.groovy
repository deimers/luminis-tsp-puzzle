@groovy.transform.CompileStatic
class TSPSolver {
	String hq
	List<Segment> segments

	List<String> uniqueCities() {
		Set<String> cities = new HashSet<String>()
		segments.each { Segment s -> cities << s.from; cities << s.to } 
		return cities as List<String>
	}

	List<String> neighbors(String city) {
		List foo = []
		List<String> neighbors = []
		segments.each { Segment s -> 
			if ( s.from == city ) { 
				neighbors << s.to	
			}
			if ( s.to == city ) { 
				neighbors << s.from	
			}
		}
		return neighbors
	}
	
	void findRoute (List<String> route, List<List<String>> result) {
		String current = route.last()					
		List<String> neighbors = neighbors(current)
		
		if ( route.size() == uniqueCities().size() && neighbors.contains(hq)) {
			route << hq
			result << route 
			return
		}
	
		List<String> unvisitedNeighbors = neighbors-route
		
		unvisitedNeighbors.each { String n ->
			List<String> croute = new ArrayList(route)
			croute << n
			findRoute(croute, result)
		}
		
	}
	
	List<List<String>> getHamiltonianRoutes(List<String> cities) {
		List<List<String>> hamiltonianRoutes = []
		findRoute([hq], hamiltonianRoutes)			
		return hamiltonianRoutes
		
	}

	int segmentLength(String from, String to) {
		return segments.find { Segment s -> (s.from == from && s.to == to) || (s.from == to && s.to == from) }?.length
	 }
 
	 int routeLength(List<String> cities) {
		 int total = 0
		 for (int i = 0; i < cities.size()-1; i++) {
			 total += segmentLength(cities[i], cities[i+1])
		 }
		 return total
	 }

	void solve() {
		List<String> cities = uniqueCities()
		
		List<List<String>> validRoutes = getHamiltonianRoutes( (List<String>) cities.findAll{ it != hq } )
		
		validRoutes.collect { ['length': routeLength(it), 'route': it] }.sort { it.length}.each {
			println it
		}

	}

	static main(args) {

		List<Segment> segments = [
		/*		
 		  new Segment(from: 'Amsterdam', to: 'Apeldoorn',   length: 85)
		, new Segment(from: 'Rotterdam', to: 'Amsterdam',   length: 80)
		, new Segment(from: 'Rotterdam', to: 'Arnhem',      length: 125)
		, new Segment(from: 'Arnhem',    to: 'Apeldoorn',   length: 35)
		, new Segment(from: 'Amsterdam', to: 'Arnhem',      length: 110)
		*/	
		  new Segment(from: 'Amsterdam',  to: 'Apeldoorn',   length: 85)
		, new Segment(from: 'Rotterdam',  to: 'Den Haag',    length: 20)
		, new Segment(from: 'Den Haag',   to: 'Amsterdam',   length: 60)
		, new Segment(from: 'Rotterdam',  to: 'Utrecht',     length: 60)
		, new Segment(from: 'Utrecht',    to: 'Arnhem',      length: 65)
		, new Segment(from: 'Arnhem',     to: 'Apeldoorn',   length: 35)
		, new Segment(from: 'Amsterdam',  to: 'Utrecht',     length: 45)

		, new Segment(from: 'Amsterdam',  to: 'Heerenveen', length: 125)
		, new Segment(from: 'Heerenveen', to: 'Apeldoorn',  length: 98)
		, new Segment(from: 'Heerenveen', to: 'Enschede',   length: 180)
		, new Segment(from: 'Apeldoorn',  to: 'Enschede',   length: 98)
		, new Segment(from: 'Utrecht',    to: 'Den Bosch',  length: 50)
		, new Segment(from: 'Utrecht',    to: 'Breda',      length: 75)
		, new Segment(from: 'Den Bosch',  to: 'Arnhem',     length: 70)
		, new Segment(from: 'Den Bosch',  to: 'Eindhoven',  length: 36)
		, new Segment(from: 'Arnhem',     to: 'Eindhoven',  length: 84)

		, new Segment(from: 'Groningen',  to: 'Heerenveen', length: 30)
		, new Segment(from: 'Groningen',  to: 'Enschede',   length: 190)
		, new Segment(from: 'Groningen',  to: 'Apeldoorn',  length: 108)

		, new Segment(from: 'Breda',      to: 'Rotterdam',  length: 55)
		, new Segment(from: 'Breda',      to: 'Den Bosch',  length: 52)
		
		, new Segment(from: 'Goes',       to: 'Rotterdam',  length: 106)
		, new Segment(from: 'Goes',       to: 'Breda',      length: 81)
		
		, new Segment(from: 'Maastricht', to: 'Eindhoven',  length: 80)
		, new Segment(from: 'Maastricht', to: 'Den Bosch',  length: 120)
		, new Segment(from: 'Maastricht', to: 'Arnhem',     length: 150)
		]

		TSPSolver solver = new TSPSolver(hq: 'Apeldoorn', segments: segments)

		solver.solve()

	}

}

class Segment {
  String from
  String to 
  int length
}