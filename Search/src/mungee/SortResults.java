package mungee;

import java.util.*;

import model.Result;

public class SortResults {

	//Sort three list and take unique and proper result from only once.
	public static List<Result> sortThree(List<Result> oldResult, List<Result> lstFirst, List<Result> lstSecond) {

		List<Result> listTwoSorted = sortTwo(lstFirst, lstSecond);
		List<Result> toRemove = new ArrayList<Result>();

		try {

			// Check if the website was previously visited. if not then only
			// keep the result.
			for (Result ts : listTwoSorted) {
				for (Result os : oldResult) {
					if (ts.getUrl().getAuthority().equals(os.getUrl().getAuthority()) && ts.getUrl().getPath()
							.replace("%22", "\"").equals(os.getUrl().getPath().replace("%22", "\""))) {
						toRemove.add(ts);
					}
				}
			}

			// Add the result ot new list if it's new;
			listTwoSorted.removeAll(toRemove);

			// Set id's to the new results.
			for (Result ss : listTwoSorted) {
				ss.setId(listTwoSorted.indexOf(ss));
			}
		} catch (Exception ex) {
			throw ex;
		}

		return listTwoSorted;
	}

	// Sort and take result from only once from the two list provided.
	public static List<Result> sortTwo(List<Result> lstFirst, List<Result> lstSecond) {
		List<Result> listFinal = new ArrayList<Result>();
		try {

			lstFirst = filterEmpty(lstFirst);
			lstSecond = filterEmpty(lstSecond);

			// Check if the result has been already given by the other service.
			for (Result se : lstSecond) {
				boolean isPresent = false;
				if (lstFirst.size() > lstSecond.indexOf(se)) {
					listFinal.add(lstFirst.get(lstSecond.indexOf(se)));
					for (Result lsFinal : listFinal) {
						if (lsFinal.getUrl().getAuthority().equals(se.getUrl().getAuthority()) && lsFinal.getUrl()
								.getPath().replace("%22", "\"").equals(se.getUrl().getPath().replace("%22", "\""))) {
							isPresent = true;
						} else {
							// Do Nothing.
						}
					}

					// Add to the filtered list if the result is new.
					if (!isPresent) {
						// System.out.println(se.getUrl());
						listFinal.add(se);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return listFinal;
	}

	//Check if the list has null url's or titles.
	public static List<Result> filterEmpty(List<Result> lstForFilter) {
		List<Result> lstfiltered = new ArrayList<Result>();
		try {
			// Code to filter data if url or title is empty.
			for (Result rst : lstForFilter) {
				if (!rst.getUrl().getAuthority().toString().isEmpty() && !rst.getTitle().isEmpty()) {

					// Code to allow only one result from one site.
					boolean isPresent = false;
					for (Result lst : lstfiltered) {
						if (lst.getUrl().getAuthority().equals(rst.getUrl().getAuthority()))
							isPresent = true;
					}

					// Add to sorteed list only if the website is not present in
					// list previously.
					if (!isPresent)
						lstfiltered.add(rst);
				}
			}
		} catch (Exception ex) {
			throw ex;
		}

		return lstfiltered;
	}
}
